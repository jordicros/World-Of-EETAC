import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value=Hierba.class, name="hierba"),
        @JsonSubTypes.Type(value=Cofre.class, name="cofre"),
        @JsonSubTypes.Type(value=Puerta.class, name="puerta")
})

public interface Celda {
    @JsonIgnore
    public int getPisable();
    @JsonIgnore
    public int getInteractuable();
}
