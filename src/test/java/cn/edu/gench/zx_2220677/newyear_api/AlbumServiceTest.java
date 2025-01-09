package cn.edu.gench.zx_2220677.newyear_api;

import cn.edu.gench.zx_2220677.newyear_api.mapper.AlbumMapper;
import cn.edu.gench.zx_2220677.newyear_api.pojo.Album;
import cn.edu.gench.zx_2220677.newyear_api.service.impl.AlbumServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Mockito;

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

    @Test
    public void testGetAllPublicImages() {
        List<Album> mockPublicAlbums = new ArrayList<>();
        Album publicAlbum = new Album();
        publicAlbum.setImageId(2L);
        publicAlbum.setFileName("public1.jpg");
        publicAlbum.setVisibility("PUBLIC");
        mockPublicAlbums.add(publicAlbum);

        when(albumMapper.findAllPublicImages()).thenReturn(mockPublicAlbums);

        List<Album> result = albumService.getAllPublicImages();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("PUBLIC", result.get(0).getVisibility());
        verify(albumMapper, times(1)).findAllPublicImages();
    }

    @Test
    public void testGetPrivateImages() {
        List<Album> mockPrivateAlbums = new ArrayList<>();
        Album privateAlbum = new Album();
        privateAlbum.setImageId(3L);
        privateAlbum.setFileName("private1.jpg");
        privateAlbum.setVisibility("PRIVATE");
        mockPrivateAlbums.add(privateAlbum);

        when(albumMapper.findPrivateImages()).thenReturn(mockPrivateAlbums);

        List<Album> result = albumService.getPrivateImages();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("PRIVATE", result.get(0).getVisibility());
        verify(albumMapper, times(1)).findPrivateImages();
    }

    @Test
    public void testGetAlbumsUploadedAsc() {
        List<Album> mockAlbums = new ArrayList<>();
        Album album1 = new Album();
        album1.setImageId(4L);
        album1.setFileName("oldest.jpg");
        mockAlbums.add(album1);

        when(albumMapper.findUploadUp()).thenReturn(mockAlbums);

        List<Album> result = albumService.getAlbumsUploadedAsc();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("oldest.jpg", result.get(0).getFileName());
        verify(albumMapper, times(1)).findUploadUp();
    }

    @Test
    public void testGetAlbumsUploadedDesc() {
        List<Album> mockAlbums = new ArrayList<>();
        Album album1 = new Album();
        album1.setImageId(5L);
        album1.setFileName("newest.jpg");
        mockAlbums.add(album1);

        when(albumMapper.findUploadDecline()).thenReturn(mockAlbums);

        List<Album> result = albumService.getAlbumsUploadedDesc();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("newest.jpg", result.get(0).getFileName());
        verify(albumMapper, times(1)).findUploadDecline();
    }

    @Test
    public void testSearchByDescription() {
        String description = "beautiful";
        List<Album> mockAlbums = new ArrayList<>();
        Album album = new Album();
        album.setImageId(6L);
        album.setFileName("nature.jpg");
        album.setDescription("A beautiful picture of nature");
        mockAlbums.add(album);

        when(albumMapper.findByDescription(description)).thenReturn(mockAlbums);

        List<Album> result = albumService.searchByDescription(description);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.get(0).getDescription().contains(description));
        verify(albumMapper, times(1)).findByDescription(description);
    }
}