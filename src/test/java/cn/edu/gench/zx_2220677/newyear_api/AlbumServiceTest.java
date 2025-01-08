package cn.edu.gench.zx_2220677.newyear_api;

import cn.edu.gench.zx_2220677.newyear_api.mapper.AlbumMapper;
import cn.edu.gench.zx_2220677.newyear_api.pojo.Album;
import cn.edu.gench.zx_2220677.newyear_api.service.impl.AlbumServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AlbumServiceTest {

    @Mock
    private AlbumMapper albumMapper;

    @InjectMocks
    private AlbumServiceImpl albumService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
       albumService.setBaseMapper(albumMapper);
    }

    @Test
    public void testGetAllAlbums() {
        List<Album> mockAlbums = new ArrayList<>();
        Album album1 = new Album();
        album1.setImageId(1L);
        album1.setFileName("test1.jpg");
        mockAlbums.add(album1);

        when(albumMapper.selectAllAlbums()).thenReturn(mockAlbums);

        List<Album> result = albumService.getAllAlbums();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("test1.jpg", result.get(0).getFileName());
        verify(albumMapper, times(1)).selectAllAlbums();
    }

    @Test
    public void testGetAlbumsByTag() {
        String tag = "New Year";
        List<Album> mockAlbums = new ArrayList<>();
        Album album = new Album();
        album.setImageId(1L);
        album.setFileName("test.jpg");
        album.setTag(tag);
        mockAlbums.add(album);

        when(albumMapper.selectAlbumsByTag(tag)).thenReturn(mockAlbums);

        List<Album> result = albumService.getAlbumsByTag(tag);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(tag, result.get(0).getTag());
        verify(albumMapper, times(1)).selectAlbumsByTag(tag);
    }
}