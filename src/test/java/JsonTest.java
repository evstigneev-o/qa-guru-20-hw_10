import com.fasterxml.jackson.databind.ObjectMapper;
import models.Order;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    @Test
    public void jsonTest() throws IOException {
        File file = new File("src/test/resources/exampleJson.json");
        Order order = new ObjectMapper().readValue(file, Order.class);
        assertEquals("1cf7bd93-6c7a-42ca-90d3-368be068103f",order.getId());
        assertEquals("completed",order.getStatus());
        assertEquals("ai92",order.getProduct().getGrade());

    }
}
