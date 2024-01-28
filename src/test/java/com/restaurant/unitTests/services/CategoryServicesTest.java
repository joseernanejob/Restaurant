package com.restaurant.unitTests.services;

import com.restaurant.exceptions.NotFoundException;
import com.restaurant.exceptions.NotNullException;
import com.restaurant.mappers.Mapper;
import com.restaurant.mocks.MockCategory;
import com.restaurant.models.Category;
import com.restaurant.repostories.CategoryRepository;
import com.restaurant.services.CategoryServices;
import com.restaurant.vos.CategoryVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

class CategoryServicesTest {

    MockCategory mock;

    @InjectMocks
    private CategoryServices service;

    @Mock
    CategoryRepository repository;

    @BeforeEach
    void setUp() {
        mock = new MockCategory();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test create new category")
    void create() {
        Category entity = mock.mockEntity(1);
        CategoryVO categoryVO = Mapper.parseObject(entity, CategoryVO.class);

        when(repository.save(entity)).thenReturn(entity);
        CategoryVO result = service.create(categoryVO);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals("Category 1", result.getName());
    }

    @Test
    @DisplayName("Test find all categories")
    void findAll(){
        List<Category> entityList = mock.mockEntityList();


        when(repository.findAll()).thenReturn(entityList);
        List<CategoryVO> result = service.findAll();

        assertNotNull(result);


        assertNotNull(result.getFirst());
        assertNotNull(result.getFirst().getId());
        assertEquals(entityList.getFirst().getName(), result.getFirst().getName());

        assertNotNull(result.get(5));
        assertNotNull(result.get(5).getId());
        assertEquals(entityList.get(5).getName(), result.get(5).getName());

        assertNotNull(result.get(13));
        assertNotNull(result.get(13).getId());
        assertEquals(entityList.get(13).getName(), result.get(13).getName());

    }

    @Test
    @DisplayName("Test find category by id")
    void findById(){
        Category category = mock.mockEntity(1);


        when(repository.findById(category.getId())).thenReturn(Optional.of(category));
        CategoryVO result = service.findById(category.getId());

        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals("Category 1", result.getName());

    }

    @Test
    @DisplayName("Test update category")
    void update() {
        Category entity = mock.mockEntity(1);
        CategoryVO categoryVO = Mapper.parseObject(entity, CategoryVO.class);

        when(repository.findById(categoryVO.getId())).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(entity);
        CategoryVO result = service.update(categoryVO);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals("Category 1", result.getName());
    }

    @Test
    @DisplayName("Test delete category")
    void delete(){
        Category entity = mock.mockEntity(1);
        when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));
        service.delete(entity.getId());
    }

    @Test
    @DisplayName("Test not found category findById")
    void notFoundFindById(){
        Exception ex = assertThrows(NotFoundException.class, () -> service.findById(-1L));
        String expectedMessage = "Category is not found!";
        String actualMessage = ex.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Test not found category update")
    void notFoundUpdate(){
        Exception ex = assertThrows(NotFoundException.class, () -> service.findById(-1L));
        String expectedMessage = "Category is not found!";
        String actualMessage = ex.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }


    @Test
    @DisplayName("Test not null create category")
    void notNullCreate(){
        Exception ex = assertThrows(NotNullException.class, () -> service.create(null));
        String expectedMessage = "Data category is null!";
        String actualMessage = ex.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Test not null update category")
    void notNullUpdate(){
        Exception ex = assertThrows(NotNullException.class, () -> service.create(null));
        String expectedMessage = "Data category is null!";
        String actualMessage = ex.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

}