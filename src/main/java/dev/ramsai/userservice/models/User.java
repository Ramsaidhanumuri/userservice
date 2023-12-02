package dev.ramsai.userservice.models;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@JsonDeserialize(as = User.class)
public class User extends BaseModel{

	private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();
}
