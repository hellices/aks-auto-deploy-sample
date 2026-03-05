package com.example.board.service;

import com.example.board.entity.Post;
import com.example.board.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post findById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다. id=" + id));
    }

    @Transactional
    public Post create(Post post) {
        return postRepository.save(post);
    }

    @Transactional
    public Post update(Long id, Post updated) {
        Post post = findById(id);
        post.setTitle(updated.getTitle());
        post.setAuthor(updated.getAuthor());
        post.setContent(updated.getContent());
        return postRepository.save(post);
    }

    @Transactional
    public void delete(Long id) {
        Post post = findById(id);
        postRepository.delete(post);
    }
}
