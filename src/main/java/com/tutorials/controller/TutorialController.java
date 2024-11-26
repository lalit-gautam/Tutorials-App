package com.tutorials.controller;

import com.tutorials.model.Tutorial;
import com.tutorials.service.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TutorialController {

    @Autowired
    private TutorialService tutorialService;

    @GetMapping("/tutorials")
    public ResponseEntity<List<Tutorial>> getAllTutorials() {
       List<Tutorial> tutorialList =  tutorialService.getAllTutorials();
       return tutorialList.isEmpty()
               ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
               : new ResponseEntity<>(tutorialList, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
        Tutorial _tutorial =  tutorialService.createTutorial(tutorial);
        return Objects.isNull(_tutorial)
                ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
    }

    @GetMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id){
        Optional<Tutorial> tutorial =  tutorialService.getTutorialById(id);
        return tutorial.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
        Optional<Tutorial> _tutorial = tutorialService.updateTutorial(id, tutorial);
        return _tutorial.map(tutorialData ->  new ResponseEntity<>(tutorialData, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/tutorials/delete/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        tutorialService.deleteTutorial(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/tutorials/delete")
    public ResponseEntity<HttpStatus> deleteAllTutorials(){
        tutorialService.deleteAllTutorials();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/tutorial/published")
    public ResponseEntity<List<Tutorial>> getTutorialByPublished(){
        List<Tutorial> _tutorialPublished = tutorialService.getTutorialByPublished(true);
        return _tutorialPublished.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(_tutorialPublished, HttpStatus.OK);
    }

    @GetMapping("/tutorial")
    public ResponseEntity<List<Tutorial>> findByTitleContaining(@RequestParam("title") String title){
        List<Tutorial> _tutorial = tutorialService.findByTitleContaining(title);
        return _tutorial.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(_tutorial, HttpStatus.OK);
    }


}
