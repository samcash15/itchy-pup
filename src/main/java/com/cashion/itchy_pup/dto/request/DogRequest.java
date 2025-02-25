@Schema(description = "Request object for creating or updating a dog")
public class DogRequest {
    @Schema(description = "Name of the dog", example = "Max")
    private String name;

    @Schema(description = "Breed of the dog", example = "Labrador Retriever")
    private String breed;

    @Schema(description = "Age of the dog in years", example = "3")
    private Integer age;
} 