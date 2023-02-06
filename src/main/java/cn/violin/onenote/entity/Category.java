package cn.violin.onenote.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private long categoryId;

    @Column(name = "category_color")
    private String categoryColor;

    @Column(name = "category_icon")
    private String categoryIcon;

    @Column(name = "category_theme")
    private String categoryTheme;

    @Column(name = "TENANT_ID")
    private String tenantId;
}
