package com.mthaler.springit.domain

import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*
import javax.validation.constraints.Size
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.GrantedAuthority

@Entity
class User(
    @Size(min = 8, max = 20)
    @Column(nullable = false, unique = true)
    var email: String,
    @Column(length = 100)
    internal var password: String,
    @Column(nullable = false)
    internal var enabled: Boolean,
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "users_roles",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
    )
    var roles: MutableSet<Role> = HashSet(),
    @Id @GeneratedValue var id: Long? = null
): UserDetails {

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

    override fun getUsername(): String = email

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true
}