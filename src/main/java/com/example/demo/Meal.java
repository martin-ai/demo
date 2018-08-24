package com.example.demo;

import java.math.BigDecimal;

/**
 * 继承接口的枚举的使用:
 * 搞个实现接口，来组织枚举，简单讲，就是分类吧。
 * 如果大量使用枚举的话，这么干，在写代码的时候，就很方便调用啦。
 */
public enum Meal {

    APPETIZER(Food.Appetizer.class),
    MAINCOURSE(Food.MainCourse.class),
    DESSERT(Food.Dessert.class),
    COFFEE(Food.Coffee.class);

    private Food[] values;

    Meal(Class<? extends Food> kind) {
        //通过class对象获取枚举实例
        values = kind.getEnumConstants();
    }

    public Food[] getValues() {
        return values;
    }

    public interface Food {

        String getName();

        BigDecimal getPrice();

        int getSort();

        enum Appetizer implements Food {
            SALAD("SALAD", BigDecimal.valueOf(Math.random())),
            SOUP("SOUP", BigDecimal.valueOf(Math.random())),
            SPRING_ROLLS("SPRING_ROLLS", BigDecimal.valueOf(Math.random()));

            private String name;
            private BigDecimal price;

            Appetizer(String name, BigDecimal price) {
                this.name = name;
                this.price = price;
            }

            @Override
            public String getName() {
                return this.name;
            }

            @Override
            public BigDecimal getPrice() {
                return this.price;
            }

            @Override
            public int getSort() {
                return this.ordinal();
            }
        }

        enum MainCourse implements Food {
            LASAGNE("LASAGNE", BigDecimal.valueOf(Math.random())),
            BURRITO("BURRITO", BigDecimal.valueOf(Math.random())),
            PAD_THAI("PAD_THAI", BigDecimal.valueOf(Math.random())),
            LENTILS("LENTILS", BigDecimal.valueOf(Math.random())),
            HUMMOUS("HUMMOUS", BigDecimal.valueOf(Math.random())),
            VINDALOO("VINDALOO", BigDecimal.valueOf(Math.random()));

            private String name;
            private BigDecimal price;

            MainCourse(String name, BigDecimal price) {
                this.name = name;
                this.price = price;
            }

            @Override
            public String getName() {
                return this.name;
            }

            @Override
            public BigDecimal getPrice() {
                return this.price;
            }

            @Override
            public int getSort() {
                return this.ordinal();
            }
        }

        enum Dessert implements Food {
            TIRAMISU("TIRAMISU", BigDecimal.valueOf(Math.random())),
            GELATO("GELATO", BigDecimal.valueOf(Math.random())),
            BLACK_FOREST_CAKE("BLACK_FOREST_CAKE", BigDecimal.valueOf(Math.random())),
            FRUIT("FRUIT", BigDecimal.valueOf(Math.random())),
            CREME_CARAMEL("CREME_CARAMEL", BigDecimal.valueOf(Math.random()));

            private String name;
            private BigDecimal price;

            Dessert(String name, BigDecimal price) {
                this.name = name;
                this.price = price;
            }

            @Override
            public String getName() {
                return this.name;
            }

            @Override
            public BigDecimal getPrice() {
                return this.price;
            }

            @Override
            public int getSort() {
                return this.ordinal();
            }
        }

        enum Coffee implements Food {
            BLACK_COFFEE("BLACK_COFFEE", BigDecimal.valueOf(Math.random())),
            DECAF_COFFEE("DECAF_COFFEE", BigDecimal.valueOf(Math.random())),
            ESPRESSO("ESPRESSO", BigDecimal.valueOf(Math.random())),
            LATTE("LATTE", BigDecimal.valueOf(Math.random())),
            CAPPUCCINO("CAPPUCCINO", BigDecimal.valueOf(Math.random())),
            TEA("TEA", BigDecimal.valueOf(Math.random())),
            HERB_TEA("HERB_TEA", BigDecimal.valueOf(Math.random()));

            private String name;
            private BigDecimal price;

            Coffee(String name, BigDecimal price) {
                this.name = name;
                this.price = price;
            }

            @Override
            public String getName() {
                return this.name;
            }

            @Override
            public BigDecimal getPrice() {
                return this.price;
            }

            @Override
            public int getSort() {
                return this.ordinal();
            }
        }
    }

}