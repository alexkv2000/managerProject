Для реализации аутентификации в Spring Java по LDAP (Active Directory) с разделением пользователей на роли
(просмотр, редактирование и добавление), вам нужно будет выполнить несколько ключевых шагов. Мы разберём процесс
настройки проекта Spring Boot с использованием Spring Security и LDAP.

Шаги для создания приложения:
Создание проекта Spring Boot:
Вы можете использовать Spring Initializr (https://start.spring.io) для создания нового проекта.
Убедитесь, что вы выбрали следующие зависимости:
Spring Web
Spring Security
Spring Data LDAP (или Spring LDAP)
Добавьте зависимости в pom.xml:Если вы используете Maven, добавьте эти зависимости:

<dependency>
<groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
<groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-ldap</artifactId>
</dependency>

Настройка application.properties:В файле src/main/resources/application.properties, добавьте конфигурацию для
подключения к вашему серверу Active Directory:

spring.ldap.urls=ldaps://your-ldap-server:636 spring.ldap.base=dc=yourdomain,dc=com spring.ldap.username=your-ldap-username
spring.ldap.password=your-ldap-password
Замените placeholder'ы на ваши реальные значения.
spring.ldap.urls=ldap://s02.gaz.ru:636
#spring.ldap.base=OU=Users,OU=KIS,OU=NN,DC=gaz,DC=ru
spring.ldap.base=DC=gaz,DC=ru
spring.ldap.username=GDVStorage
spring.ldap.password=

Конфигурация Spring Security:Создайте класс конфигурации безопасности, чтобы настроить аутентификацию LDAP и роли пользователей.

import org.springframework.context.annotation.Bean; import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
@Bean
public ActiveDirectoryLdapAuthenticationProvider authProvider() {
return new ActiveDirectoryLdapAuthenticationProvider("your-domain.com", "ldaps://your-ldap-server:636");
}
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
auth.authenticationProvider(authProvider());
}
@Override protected void configure(HttpSecurity http) throws Exception {
http
.authorizeRequests()
.antMatchers("/view/**")
.hasAnyRole("VIEWER", "EDITOR", "ADMIN")
.antMatchers("/edit/**")
.hasAnyRole("EDITOR", "ADMIN")
.antMatchers("/add/**").hasRole("ADMIN")
.anyRequest().authenticated()
.and()
.formLogin()
.permitAll()
.and()
.logout()
.permitAll(); }
}
Создание контроллеров:Теперь создайте контроллеры для работы с вашими URL-адресами (просмотр, редактирование и добавление).

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/view")
public class ViewController {
@GetMapping
    public String viewPage() {
        return "viewPage"; // имя вашего HTML-шаблона
    }
}
@Controller
@RequestMapping("/edit")
public class EditController {
    @GetMapping public String editPage() {
        return "editPage"; // имя вашего HTML-шаблона
    }
}
@Controller
@RequestMapping("/add")
public class AddController {
    @GetMapping public String addPage() {
        return "addPage"; // имя вашего HTML-шаблона
    }
}
Создание HTML-шаблонов:Создайте HTML-файлы для представления пользователей с использованием Thymeleaf.
Например, viewPage.html, editPage.html, addPage.html, которые будут содержать нужный вам контент для просмотра,
редактирования и добавления.
Тестирование приложения:Запустите ваше приложение и протестируйте аутентификацию через LDAP.
Убедитесь, что права доступа настроены корректно, и пользователи, находящиеся в разных ролях (VIEWER, EDITOR, ADMIN),
могут открыть только свои соответствующие страницы.
Замечания
Убедитесь, что роли пользователей настроены в Active Directory (по умолчанию они могут не быть указаны).
Вы можете интегрировать дополнительные механизмы для управления ролями пользователей в вашем приложении, если это необходимо.
Также вы можете использовать более сложные подходы для хранения ролей и прав доступа, например, в базе данных или через внешние API.
Это общая схема реализации аутентификации через LDAP с разделением прав доступа. Настройки могут варьироваться
в зависимости от ваших конкретных требований и окружения.


----

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/public/**").permitAll() // разрешить доступ к публичным URL
                        .anyRequest().authenticated()) // остальные защищены
                .formLogin(withDefaults()).logout(withDefaults()); // или другие способы аутентификации
        return http.build();
    }
}