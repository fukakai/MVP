package fr.dalichamp.trainingcrud.entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
	private List<Book> books;
	@Column @NotBlank(message = "First Name Required")
	private String firstname;
	@Column @NotBlank(message = "Last Name Required")
	private String lastname;
}
