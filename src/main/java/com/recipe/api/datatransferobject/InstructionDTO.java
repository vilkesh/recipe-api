package com.recipe.api.datatransferobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.NotBlank;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class InstructionDTO {
    private Long id;
    @NotBlank(message = "instructions can not be blank")
    private String instruction;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
}
