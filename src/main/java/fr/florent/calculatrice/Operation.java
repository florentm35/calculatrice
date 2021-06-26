package fr.florent.calculatrice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Operation {

    private Operation subValue;
    private double value = -1;
    private EnumOperator operator;
    private transient Operation leftOperation;
    private Operation rightOperation;

    @Override
    public String toString() {

        final GsonBuilder builder = new GsonBuilder();
        final Gson gson = builder.create();

        return gson.toJson(this);

    }

}
