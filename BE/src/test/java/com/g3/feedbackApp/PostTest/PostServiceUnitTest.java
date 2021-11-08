package com.g3.feedbackApp.PostTest;

import com.g3.feedbackApp.DataSources.FakeDataSourcePost;
import com.g3.feedbackApp.DataSources.Interfaces.IDataSourcePost;
import com.g3.feedbackApp.Models.PostModel;
import com.g3.feedbackApp.Services.Interfaces.IPostService;
import com.g3.feedbackApp.Services.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class PostServiceUnitTest {

    private IPostService postService;
    private IDataSourcePost dataSource;

    @BeforeEach
    public void preparePostServiceUnitTest(){
        this.dataSource = new FakeDataSourcePost();
        this.postService = new PostService(dataSource);
    }

    @Test
    public void getPostModelWithIdSuccessfulTest(){
        PostModel modelToExpect = new PostModel(4L, 1,"First Post", "code", "my first code post", LocalDate.now(), null);
        //create list of reviewers to perform create post method
        List<Long> reviewersIds = new ArrayList<>();
        reviewersIds.add(2L);

        postService.createPost(modelToExpect, Path.of("testFilePath"), reviewersIds);

        assertEquals(modelToExpect, postService.getPostWithId(4L));
    }

    @Test
    public void getPostModelWithIdFailTest(){
        PostModel postModel = postService.getPostWithId(100L);
        assertNull(postModel);
    }
}
