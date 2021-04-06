package com.mthaler.springit.domain

import com.mthaler.springit.domain.validator.PasswordsMatch
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*
import javax.validation.constraints.Size
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.GrantedAuthority
import javax.validation.constraints.NotEmpty

@Entity
@PasswordsMatch
class User(
    @Size(min = 8, max = 20)
    @Column(nullable = false, unique = true)
    var email: String = "",
    @Column(length = 100)
    internal var password: String = "",
    @Column(nullable = false)
    internal var enabled: Boolean = false,
    @NotEmpty(message = "You must enter First Name.")
    var firstName: String = "",
    @NotEmpty(message = "You must enter Last Name.")
    var lastName: String = "",
    @NotEmpty(message = "Please enter alias.")
    @Column(nullable = false, unique = true)
    var alias: String = "",
): UserDetails {

    @Id @GeneratedValue var id: Long? = null

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "users_roles",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
    )
    var roles: MutableSet<Role> = HashSet()

    @Transient
    @NotEmpty(message = "Please enter Password Confirmation")
    var confirmPassword: String = ""

    var activationCode: String = ""

    fun addRole(role: Role) {
        roles.add(role)
    }

    fun addRoles(roles: Set<Role>) {
        roles.forEach(::addRole)
    }

    override fun getAuthorities(): Collection<GrantedAuthority> = roles.map { SimpleGrantedAuthority(it.name) }

    override fun getPassword(): String = password

    fun setPassword(value: String) {
        this.password = password
    }

    override fun isEnabled(): Boolean = enabled

    fun setEnabled(value: Boolean) {
        enabled = value
    }

    val fullName: String
        get() = firstName + " " + lastName

    override fun getUsername(): String = email

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true
}