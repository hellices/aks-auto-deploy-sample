package com.example.board;

import com.example.board.entity.Post;
import com.example.board.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class BoardApplicationTests {

    @Autowired
    private PostService postService;

    @Test
    void contextLoads() {
    }

    @Test
    void createPost() {
        Post post = new Post();
        post.setTitle("테스트 제목");
        post.setAuthor("테스터");
        post.setContent("테스트 내용입니다.");

        Post saved = postService.create(post);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getTitle()).isEqualTo("테스트 제목");
        assertThat(saved.getAuthor()).isEqualTo("테스터");
        assertThat(saved.getContent()).isEqualTo("테스트 내용입니다.");
        assertThat(saved.getCreatedAt()).isNotNull();
    }

    @Test
    void findAllPosts() {
        Post post1 = new Post();
        post1.setTitle("첫 번째 글");
        post1.setAuthor("작성자1");
        post1.setContent("첫 번째 내용");
        postService.create(post1);

        Post post2 = new Post();
        post2.setTitle("두 번째 글");
        post2.setAuthor("작성자2");
        post2.setContent("두 번째 내용");
        postService.create(post2);

        List<Post> posts = postService.findAll();
        assertThat(posts).hasSizeGreaterThanOrEqualTo(2);
    }

    @Test
    void updatePost() {
        Post post = new Post();
        post.setTitle("원래 제목");
        post.setAuthor("원래 작성자");
        post.setContent("원래 내용");
        Post saved = postService.create(post);

        Post updated = new Post();
        updated.setTitle("수정된 제목");
        updated.setAuthor("수정된 작성자");
        updated.setContent("수정된 내용");
        Post result = postService.update(saved.getId(), updated);

        assertThat(result.getTitle()).isEqualTo("수정된 제목");
        assertThat(result.getAuthor()).isEqualTo("수정된 작성자");
        assertThat(result.getContent()).isEqualTo("수정된 내용");
    }

    @Test
    void deletePost() {
        Post post = new Post();
        post.setTitle("삭제할 글");
        post.setAuthor("작성자");
        post.setContent("삭제할 내용");
        Post saved = postService.create(post);

        postService.delete(saved.getId());

        assertThatThrownBy(() -> postService.findById(saved.getId()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
