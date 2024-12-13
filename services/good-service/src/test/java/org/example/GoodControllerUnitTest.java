package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GoodControllerUnitTest {

    @Mock
    private GoodRepository goodRepository;
    @Mock
    private UserClient userClient;
    @InjectMocks
    private GoodController goodController;

    @Test
    void testGetUserFullname() {
        when(userClient.getAllUsers())
                .thenReturn(List.of(
                        User.builder()
                                .id(4L)
                                .build(),
                        User.builder()
                                .id(5L)
                                .build(),
                        User.builder()
                                .id(6L)
                                .build()
                ));
        when(goodRepository.findByUserId(anyLong()))
                .thenReturn(Good.builder()
                        .fullname("testFullName")
                        .build());

        Good actualFullname = goodController.getUserFullname(4L);

        assertThat(actualFullname.getFullname()).isEqualTo("testFullName");
    }
}