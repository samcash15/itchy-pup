@Entity
@Table(name = "symptom_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SymptomLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dog_id", nullable = false)
    private Dog dog;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private Integer itchLevel;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<Symptom> symptoms = new HashSet<>();

    @Embedded
    private WeatherData weatherData;

    private String notes;

    @PrePersist
    protected void onCreate() {
        date = LocalDateTime.now();
    }
}

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherData {
    private Double temperature;
    private Double humidity;
    private Double pollenCount;
    private String weatherCondition;
}