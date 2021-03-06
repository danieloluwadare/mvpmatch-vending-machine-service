package com.mvpMatch.vendingmachineservice.service

import com.mvpMatch.vendingmachineservice.exceptions.ProductException
import com.mvpMatch.vendingmachineservice.model.Product
import com.mvpMatch.vendingmachineservice.model.dtos.ProductDto
import com.mvpMatch.vendingmachineservice.repository.ProductRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class ProductServiceImpl(private val productRepository: ProductRepository) : ProductService {
    private val log = LoggerFactory.getLogger(javaClass)

    override fun create(productDto: ProductDto): Product {
        log.info("create")
        if (productDto.cost % 5 != 0) throw ProductException("cost must be in multiple of 5", "")
        val product = Product()
        product.productName = productDto.productName
        product.cost = productDto.cost
        product.amountAvailable = productDto.amountAvailable
        product.seller = productDto.getPrincipalUser()
        return productRepository.save(product)
    }

    override fun findById(id: Long): Product {
        log.info("get product by id ==> $id")
        val product = productRepository.findByIdAndDeletedAtIsNull(id)
        if (!product.isPresent) throw ProductException("product with id of $id not found", "")
        log.info("done  product by id ==> $id")
        return product.get()
    }

    override fun findAll(): List<Product> {
        return productRepository.findAll()
    }

    override fun update(productDto: ProductDto, id: Long): Product {
        if (productDto.cost % 5 != 0) throw ProductException("cost must be in multiple of 5", "")
        val product = findById(id)
        product.productName = productDto.productName
        product.cost = productDto.cost
        product.amountAvailable = productDto.amountAvailable
        return productRepository.save(product)
    }

    @Transactional
    override fun delete(id: Long) {
        productRepository.updateDeletedAt(Date(), id)
    }
}