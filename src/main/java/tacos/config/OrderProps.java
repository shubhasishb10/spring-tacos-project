package tacos.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author Shubhasish
 */
@Component
@ConfigurationProperties(prefix = "tacos.orders")
@Validated
public class OrderProps {

    @Min(value =  5, message = "must be between 5 and 25")
    @Max(value =  25, message = "must be between 5 and 25")
    private int pageSize;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
