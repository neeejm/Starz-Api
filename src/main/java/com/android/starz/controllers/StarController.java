package com.android.starz.controllers;

import java.util.List;

import javax.persistence.ExcludeDefaultListeners;

import com.android.starz.model.Star;
import com.android.starz.repository.StarRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/stars")
@RestController
public class StarController {

    @Autowired
    private StarRepository starRepository;

    @GetMapping
    public List<Star> get() {
        return starRepository.findAll();
    }

    @GetMapping("/{id}")
    public Star get(@PathVariable(name = "id") int idStar) {
        return starRepository.findById(idStar).get();
    }

    @PostMapping
    public void add(@RequestBody Star star) {
        starRepository.save(star);
    }

    @PutMapping
    public void update(@RequestBody Star star) {
        Star mStar = starRepository.getById(star.getId());

        if (mStar != null) {
            mStar.setNom(star.getNom());
            mStar.setPrenom(star.getPrenom());
            mStar.setVille(star.getVille());
            mStar.setImageURL(star.getImageURL());
            starRepository.save(mStar);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") int idStar) {
        starRepository.deleteById(idStar);
    }
}
