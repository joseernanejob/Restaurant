package com.restaurant.services;

import com.restaurant.exceptions.AppException;
import com.restaurant.exceptions.NotFoundException;
import com.restaurant.exceptions.NotNullException;
import com.restaurant.mappers.Mapper;
import com.restaurant.models.Category;
import com.restaurant.models.Product;
import com.restaurant.repostories.CategoryRepository;
import com.restaurant.repostories.ProductRepository;
import com.restaurant.vos.product.RequestProductVO;
import com.restaurant.vos.product.ProductVO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductServices {

    ProductRepository repository;

    CategoryRepository categoryRepository;


    public ProductServices(ProductRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    public ProductVO create(RequestProductVO data){
        if(data ==null) throw new NotNullException("Data product is null!");
        Category category = categoryRepository.findById(data.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category is not found!"));

        Product product = Mapper.parseObject(data, Product.class);
        product.setCategory(category);
        return Mapper.parseObject(repository.save(product), ProductVO.class);
    }

    public List<ProductVO> findAll(){
        return Mapper.parseListObjects(repository.findAll(), ProductVO.class);
    }

    public List<ProductVO> findAllByCategory(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category is not found!"));
        return Mapper.parseListObjects(repository.findAllByCategoryId(id), ProductVO.class);
    }

    public ProductVO findById(Long id){
        Product product = repository.findById(id).orElseThrow(() -> new NotFoundException("Product is not found!"));
        return Mapper.parseObject(product, ProductVO.class);
    }

    public ProductVO update(RequestProductVO data){
        if(data ==null) throw new NotNullException("Data product is null!");
        Product product = repository.findById(data.getId()).orElseThrow(() -> new NotFoundException("Product is not found!"));
        if (!Objects.equals(product.getName(), data.getName())){
            boolean exists = repository.existsProductByName(data.getName());
            if (exists) throw  new AppException("Product name already registered! ", HttpStatus.BAD_REQUEST);
        }
        product.updateProduct(data);

        if(!Objects.equals(product.getCategory().getId(), data.getCategoryId())){
            Category category = categoryRepository.findById(data.getCategoryId())
                    .orElseThrow(() -> new NotFoundException("Category is not found!"));
            product.setCategory(category);
        }

        return Mapper.parseObject(repository.save(product), ProductVO.class);
    }

    public void delete(Long id){
        Product product = repository.findById(id).orElseThrow(() -> new NotFoundException("Product is not found!"));
        repository.delete(product);
    }

}
