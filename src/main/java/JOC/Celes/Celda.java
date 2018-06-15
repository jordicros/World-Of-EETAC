package JOC.Celes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value=Hierba.class, name="hierba"),
        @JsonSubTypes.Type(value=Cofre.class, name="cofre"),
        @JsonSubTypes.Type(value=Puerta.class, name="puerta"),
        @JsonSubTypes.Type(value=Puerta.class, name="ventana"),
        @JsonSubTypes.Type(value=Puerta.class, name="trampilla"),
        @JsonSubTypes.Type(value=Puerta.class, name="paret"),
        @JsonSubTypes.Type(value=Rio.class, name="Rio"),
})

public abstract class Celda {
    @JsonIgnore
    public abstract int getPisablePersonaje();
    @JsonIgnore
    public abstract int getInteractuable();
    @JsonIgnore
    public abstract int getPisableZombie();
    @JsonIgnore
    public abstract String getSimbolo();
}
