package models;

import lombok.Data;

@Data
public class Order {
    private String columnId;
    private Created created;
    private String id;
    private Product product;
    private String stationId;
    private String status;
    private Boolean postpaid;
    private Updated updated;

    @Data
    public static class Created{
        private Integer cost;
        private String datetimeUtc;
        private Long timestampUtc;
        private Integer volume;
    }

    @Data
    public static class Product{
        private String grade;
        private String id;
        private String name;
        private Integer price;
    }

    @Data
    public static class Updated{
        private Integer cost;
        private String datetimeUtc;
        private String code;
        private String message;
        private Long timestampUtc;
        private Integer volume;
    }
}
