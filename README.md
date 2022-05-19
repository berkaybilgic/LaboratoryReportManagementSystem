# Laboratory Report Management System

Projede hasta bilgileri ile oluşturulan raporların yönetilmesi amaçlanmıştır.

## Architecture
![lab](https://user-images.githubusercontent.com/56771957/168907321-1936460e-3d62-4807-92a8-38b07067036e.jpg)


#### Project Features

* Rapor Tanımı ( Dosya numarası, Hasta Ad ve Soyad, Hasta Kimlik
Numarası(TC), Koyulan Tanı Başlığı, Tanı Detayları, Raporun Verildiği
Tarih, Fiziksel Rapora Ait .png/.jpg Formatında Bir Adet Fotoğraf )
* Bir rapor yalnızca bir laborant tarafından tanımlanabilir. Bir
laborant ise  n tane rapor tanımlayabilir. ( Ad, Soyad, Rol Yetkisi, Email , Kullanıcı Adı ,Hastane Kimlik
Numarası(7 Haneli), )
* Hasta adı/soyadı, Hasta kimlik numarası, Laborant adı/soyadı
bilgileri ile arama yapabilir. Rapor tarihi ile sıralama
yapabilir
* Var olan bir rapor üzerinde değişiklik yapılabilir
* Var olan tüm raporların detayları incelenebilir
* Var olan bir rapor silinebilir
* Kullanıcılar sisteme kullanıcı adı/parola ile giriş yapabilir
* Bir yetkilendirme mekanizması içeriyor. Örneğin standart kullanıcılar
kayıt oluşturabilir ilişkilendirebilir fakat silemez. Yönetici tüm
eylemleri gerçekleştirebilir
* Pagination ile sayfalama işlemi yapıldı.
* Custom error page handling yapıldı
* İmagelar byte olarak PostgreSQLde tutuldu.

**Dependencies:** Java 11.0.14 , Maven, PostgreSQL 14.0

### Database Configuration

Database connection için aşağıdaki alanları **application.properties** üzerinden kendi connection bilgilerinize göre ayarlamalısınız veya bu bilgiler ile kullanıcı oluşturmalısınız.

`spring.datasource.url=jdbc:postgresql://localhost:5432/reportdb`

`spring.datasource.username=postgres`

`spring.datasource.password=12345`

### Create Database
`createdb -h localhost -p 5432 -U postgres reportdb`

or 

```sh
$ docker run --name reportdb --rm -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=12345 -e PGDATA=/var/lib/postgresql/data/pgdata -v /tmp:/var/lib/postgresql/data -p 5432:5432 -it postgres:14.1-alpine

$ docker exec -it reportdb psql -U postgres -c "CREATE DATABASE reportdb"
```

### Install depedencies

```sh
$ mvn clean install
```

### Start

```sh
$ mvn spring-boot:run
```
or

```sh
$ java -jar target/Report-0.0.1-SNAPSHOT.jar
```

### Login Information


- Web sayfası  kullanıcılara açıktır. Web sayfası açıldıktan sonra bir **ADMIN** kullanıcısı veya **USER** kullanıcısı oluşturulabilir.


- Login işlemi kullanıcı adı ve password ile gerçekleşir.


- **USER** kendi eklediği raporları görüntüleyebilir ve değiştirebilir ancak silemez.


