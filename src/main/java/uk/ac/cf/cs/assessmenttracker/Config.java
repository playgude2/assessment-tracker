package uk.ac.cf.cs.assessmenttracker;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("app.property")
public class Config {
    private int defaultStartYear;

    public int getDefaultStartYear() {
        return defaultStartYear;
    }
    public void setDefaultStartYear(Integer aDefaultStartYear){
        this.defaultStartYear = aDefaultStartYear;
    }
}
