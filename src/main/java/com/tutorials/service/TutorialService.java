package com.tutorials.service;

import com.tutorials.model.Tutorial;
import com.tutorials.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class TutorialService {

    @Autowired
    private TutorialRepository tutorialRepository;

    public List<Tutorial> getAllTutorials() {
       return tutorialRepository.findAll();
    }

    public Tutorial createTutorial(Tutorial tutorial){
        return tutorialRepository.save(tutorial);
    }

    public Optional<Tutorial> getTutorialById(long id){
        return tutorialRepository.findById(id);
    }

    public Optional<Tutorial> updateTutorial(long id, Tutorial tutorial){
        return tutorialRepository.findById(id)
                .map(existingTutorial -> {
                    existingTutorial.setName(tutorial.getName());
                    existingTutorial.setDescription(tutorial.getDescription());
                    existingTutorial.setPublished(tutorial.isPublished());
                    return tutorialRepository.save(existingTutorial);
                });
    }

    public void deleteTutorial(long id) {
        tutorialRepository.deleteById(id);
    }

    public void deleteAllTutorials(){
         tutorialRepository.deleteAll();
    }

    public List<Tutorial> getTutorialByPublished(boolean published){
        return tutorialRepository.findByPublished(published);
    }

    public List<Tutorial> findByTitleContaining(String name){
        return tutorialRepository.findByNameContaining(name);
    }
}
