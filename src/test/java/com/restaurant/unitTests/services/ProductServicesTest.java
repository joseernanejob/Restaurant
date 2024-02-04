package com.restaurant.unitTests.services;

import com.restaurant.exceptions.NotFoundException;
import com.restaurant.exceptions.NotNullException;
import com.restaurant.mappers.Mapper;
import com.restaurant.mocks.MockProduct;
import com.restaurant.models.Product;
import com.restaurant.repostories.CategoryRepository;
import com.restaurant.repostories.ProductRepository;
import com.restaurant.services.ProductServices;
import com.restaurant.vos.product.RequestProductVO;
import com.restaurant.vos.product.ProductVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProductServicesTest {

    MockProduct mock;

    @InjectMocks
    private ProductServices service;

    @Mock
    ProductRepository repository;

    @Mock
    CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        mock = new MockProduct();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test create new product")
    void create() {
        Product entity = mock.mockEntity(1);
        RequestProductVO productVO = Mapper.parseObject(entity, RequestProductVO.class);
        productVO.setCategoryId(entity.getCategory().getId());

        when(categoryRepository.findById(productVO.getCategoryId())).thenReturn(Optional.ofNullable(entity.getCategory()));
        when(repository.save(entity)).thenReturn(entity);
        ProductVO result = service.create(productVO);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals("Produto 1", result.getName());
        assertEquals("Descrição produto 1", result.getDescription());
        assertEquals(1L, result.getCategory().getId());
        assertEquals("Bebida", result.getCategory().getName());
    }

    @Test
    @DisplayName("Test find all products")
    void findAll(){
        List<Product> entityList = mock.mockEntityList();


        when(repository.findAll()).thenReturn(entityList);
        List<ProductVO> result = service.findAll();

        assertNotNull(result);

        assertNotNull(result.getFirst());
        assertNotNull(result.getFirst().getId());
        assertEquals("Produto 0", result.getFirst().getName());
        assertEquals("Descrição produto 0", result.getFirst().getDescription());
        assertEquals(2L, result.getFirst().getCategory().getId());
        assertEquals("Sobremesa", result.getFirst().getCategory().getName());


        assertNotNull(result.get(5));
        assertNotNull(result.get(5).getId());
        assertEquals("Produto 5", result.get(5).getName());
        assertEquals("Descrição produto 5", result.get(5).getDescription());
        assertEquals(1L, result.get(5).getCategory().getId());
        assertEquals("Bebida", result.get(5).getCategory().getName());

        assertNotNull(result.get(13));
        assertNotNull(result.get(13).getId());
        assertEquals("Produto 13", result.get(13).getName());
        assertEquals("Descrição produto 13", result.get(13).getDescription());
        assertEquals(1L, result.get(13).getCategory().getId());
        assertEquals("Bebida", result.get(13).getCategory().getName());

    }


    @Test
    @DisplayName("Test find product by id")
    void findById(){
        Product product = mock.mockEntity(1);


        when(repository.findById(product.getId())).thenReturn(Optional.of(product));
        ProductVO result = service.findById(product.getId());

        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals("Produto 1", result.getName());
        assertEquals("Descrição produto 1", result.getDescription());
        assertEquals(1L, result.getCategory().getId());
        assertEquals("Bebida", result.getCategory().getName());

    }

    @Test
    @DisplayName("Test update product")
    void update() {
        Product entity = mock.mockEntity(1);
        RequestProductVO productVO = Mapper.parseObject(entity, RequestProductVO.class);
        productVO.setCategoryId(entity.getCategory().getId());

        when(categoryRepository.findById(productVO.getCategoryId())).thenReturn(Optional.ofNullable(entity.getCategory()));
        when(repository.findById(productVO.getId())).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(entity);
        ProductVO result = service.update(productVO);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals("Produto 1", result.getName());
        assertEquals("Descrição produto 1", result.getDescription());
        assertEquals(1L, result.getCategory().getId());
        assertEquals("Bebida", result.getCategory().getName());
    }

    @Test
    @DisplayName("Test delete product")
    void delete(){
        Product entity = mock.mockEntity(1);
        when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));
        service.delete(entity.getId());
    }

    @Test
    @DisplayName("Test not found product findById")
    void notFoundFindById(){
        Exception ex = assertThrows(NotFoundException.class, () -> service.findById(-1L));
        String expectedMessage = "Product is not found!";
        String actualMessage = ex.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Test not found product delete")
    void notFoundDelete(){
        Exception ex = assertThrows(NotFoundException.class, () -> service.delete(-1L));
        String expectedMessage = "Product is not found!";
        String actualMessage = ex.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Test not found product update")
    void notFoundUpdate(){
        Product entity = mock.mockEntity(1);
        RequestProductVO productVO = Mapper.parseObject(entity, RequestProductVO.class);
        productVO.setId(-1L);
        Exception ex = assertThrows(NotFoundException.class, () -> service.update(productVO));
        String expectedMessage = "Product is not found!";
        String actualMessage = ex.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }


    @Test
    @DisplayName("Test not null create product")
    void notNullCreate(){
        Exception ex = assertThrows(NotNullException.class, () -> service.create(null));
        String expectedMessage = "Data product is null!";
        String actualMessage = ex.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Test not null update product")
    void notNullUpdate(){
        Exception ex = assertThrows(NotNullException.class, () -> service.update(null));
        String expectedMessage = "Data product is null!";
        String actualMessage = ex.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }


    @Test
    @DisplayName("Test not found category product update")
    void notFoundCategoryProductUpdate(){
        Product entity = mock.mockEntity(1);
        RequestProductVO productVO = Mapper.parseObject(entity, RequestProductVO.class);
        productVO.setCategoryId(-1L);

        when(repository.findById(productVO.getId())).thenReturn(Optional.of(entity));
        Exception ex = assertThrows(NotFoundException.class, () -> service.update(productVO));
        String expectedMessage = "Category is not found!";
        String actualMessage = ex.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }



    @Test
    @DisplayName("Test not found category product create")
    void notFoundCategoryProductCreate(){
        Product entity = mock.mockEntity(1);
        RequestProductVO productVO = Mapper.parseObject(entity, RequestProductVO.class);
        productVO.setCategoryId(-1L);

        when(repository.findById(productVO.getId())).thenReturn(Optional.of(entity));
        Exception ex = assertThrows(NotFoundException.class, () -> service.create(productVO));
        String expectedMessage = "Category is not found!";
        String actualMessage = ex.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }



}