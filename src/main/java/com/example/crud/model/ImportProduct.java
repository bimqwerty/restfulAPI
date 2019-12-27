package com.example.crud.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "import_product", schema = "productmanager", catalog = "")
public class ImportProduct {
	@Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	
	 
	@Column(name = "import_id")
    private Integer importId;
	
    
    @Column(name = "product_id")
    private Integer productId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

  
    public Integer getImportId() {
        return importId;
    }

    public void setImportId(Integer importId) {
        this.importId = importId;
    }


    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }


}
