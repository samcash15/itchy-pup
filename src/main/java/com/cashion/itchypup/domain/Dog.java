
@Entity
@Table(name = "dogs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String breed;

    private Integer age;
    
    private Double weight;

    @ElementCollection
    private List<String> knownAllergies = new ArrayList<>();

    @OneToMany(mappedBy = "dog", cascade = CascadeType.ALL)
    private List<Medication> medications = new ArrayList<>();

    @OneToMany(mappedBy = "dog", cascade = CascadeType.ALL)
    private List<SymptomLog> symptomLogs = new ArrayList<>();

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Dog{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", breed='" + breed + '\'' +
            ", age=" + age +
            ", weight=" + weight +
            '}';
    }
}   