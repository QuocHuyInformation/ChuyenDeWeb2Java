package com.aaa.exercise03.controller;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aaa.exercise03.entity.Tag;
import com.aaa.exercise03.service.TagService;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("api/tags")
public class TagController {

    private TagService tagService;

    // Create Tag REST API
    @PostMapping
    public ResponseEntity<Tag> createTag(@RequestBody Tag tag) {
        Tag savedTag = tagService.createTag(tag);
        return new ResponseEntity<>(savedTag, HttpStatus.CREATED);
    }

    // Get Tag by id REST API
    // http://localhost:8080/api/tags/{id}
    @GetMapping("{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable("id") UUID tagId) {
        Tag tag = tagService.getTagById(tagId);
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    // Get All Tags REST API
    // http://localhost:8080/api/tags
    @GetMapping
    public ResponseEntity<List<Tag>> getAllTags() {
        List<Tag> tags = tagService.getAllTags();
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    // Update Tag REST API
    @PutMapping("{id}")
    // http://localhost:8080/api/tags/{id}
    public ResponseEntity<Tag> updateTag(@PathVariable("id") UUID tagId,
                                                      @RequestBody Tag tag) {
        tag.setId(tagId);
        Tag updatedTag = tagService.updateTag(tag);
        if (updatedTag != null) {
            return new ResponseEntity<>(updatedTag, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete Tag REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTag(@PathVariable("id") UUID tagId) {
        tagService.deleteTag(tagId);
        return new ResponseEntity<>("Tag successfully deleted!", HttpStatus.OK);
    }
}
