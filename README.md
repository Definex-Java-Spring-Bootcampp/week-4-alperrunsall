
---
## Kullanılan Servisler ve İşlevleri

### 1. User Servisi
- Kullanıcıların oluşturulması, güncellenmesi ve silinmesi gibi kullanıcı işlemlerini yönetir.
- Kullanıcıların bankalara başvuru yapmalarını sağlar.

### 2. Bank Servisi
- Banka bilgilerinin alınması ve listelenmesi gibi banka işlemlerini yönetir.
- Kullanıcıların bankalara başvuru yapmalarını kabul eder.

### 3. Campaign Servisi
- Mevcut kampanyaları almak ve listelemek gibi kampanya işlemlerini yönetir.

### 4. Loan Servisi
- Kredi bilgilerini almak ve listelemek gibi kredi işlemlerini yönetir.

### 5. Application Servisi
- Kullanıcıların bankalara yaptığı başvuruları işler.

---

## Kullanım Senaryoları

### 1. Yeni Kullanıcı Oluşturma

**Endpoint**: `POST localhost:8080/users`

**Açıklama**: Yeni bir kullanıcı oluşturur.

**Örnek İstek**:
```json
{
    "name": "alper",
    "surname": "ünsal",
    "email": "asd@gmail.com",
    "password": "password",
    "phoneNumber": 5394443322,
    "active": true
}
```

### 2. Bankaya Başvuru Yapma

**Endpoint**: `POST localhost:8080/applications`

**Açıklama**: Kullanıcı, belirli bir bankaya kredi başvurusu yapar.

**Örnek İstek**:
```json
{
    "email": "asd@gmail.com",
    "bank": "BANKAADI"
}
```

### 3. Kampanyaları Listeleme

**Endpoint**: `GET localhost:8080/campaigns`

**Açıklama**: Mevcut kampanyaların bir listesini alır.

### 4. Kullanıcı Bilgilerini Alma

**Endpoint**: `GET localhost:8080/users/{userMail}`

**Açıklama**: Belirli bir kullanıcının bilgilerini alır.

---

## Teknolojik Altyapı

- **RabbitMQ**: Servisler arasında asenkron iletişimi sağlamak için kullanılır.
- **FeignClient**: Mikroservisler arasındaki HTTP isteklerini kolaylaştırmak için kullanılır.
- **PostgreSQL**: Veritabanı olarak kullanılır.
- **Redis**: Önbellekleme amacıyla kullanılır.
- **Eureka Server**: Servislerin bulunabilirliğini sağlamak için kullanılır.
- **Gateway**: Gelen istekleri yönlendirmek ve güvenlik sağlamak için kullanılır.
- **Kafka**: Olay yönetimi ve mesaj kuyruğu olarak kullanılacak.
- **MongoDB**: Mesajlar ve diğer verilerin saklanması için kullanılacak NoSQL veritabanı.

---
