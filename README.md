
---

## Mikroservisler ve İşlevleri

### 1. Kullanıcı Servisi
- Kullanıcıların oluşturulması, güncellenmesi ve silinmesi gibi kullanıcı işlemlerini yönetir.
- Kullanıcıların bankalara başvuru yapmalarını sağlar.

### 2. Banka Servisi
- Banka bilgilerinin alınması ve listelenmesi gibi banka işlemlerini yönetir.
- Kullanıcıların bankalara başvuru yapmalarını kabul eder.

### 3. Kampanya Servisi
- Mevcut kampanyaları almak ve listelemek gibi kampanya işlemlerini yönetir.

### 4. Kredi Servisi
- Kredi bilgilerini almak ve listelemek gibi kredi işlemlerini yönetir.

### 5. Başvuru Servisi
- Kullanıcıların bankalara yaptığı başvuruları işler.

---

## Kullanım Senaryoları

### 1. Yeni Kullanıcı Oluşturma

**Endpoint**: `POST /users`

**Açıklama**: Yeni bir kullanıcı oluşturur.

**Örnek İstek**:
```json
{
  "name": "John Doe",
  "email": "john.doe@example.com"
}
```

### 2. Bankaya Başvuru Yapma

**Endpoint**: `POST /applications`

**Açıklama**: Kullanıcı, belirli bir bankaya kredi başvurusu yapar.

**Örnek İstek**:
```json
{
  "userId": "123456",
  "bankId": "789012",
  "amount": 10000,
  "duration": 12
}
```

### 3. Kampanyaları Listeleme

**Endpoint**: `GET /campaigns`

**Açıklama**: Mevcut kampanyaların bir listesini alır.

### 4. Kullanıcı Bilgilerini Alma

**Endpoint**: `GET /users/{userId}`

**Açıklama**: Belirli bir kullanıcının bilgilerini alır.

**Örnek Yanıt**:
```json
{
  "id": "123456",
  "name": "John Doe",
  "email": "john.doe@example.com"
}
```

---

## Teknolojik Altyapı

- **RabbitMQ**: Servisler arasında asenkron iletişimi sağlamak için kullanılır.
- **FeignClient**: Mikroservisler arasındaki HTTP isteklerini kolaylaştırmak için kullanılır.
- **PostgreSQL**: Veritabanı olarak kullanılır.
- **Redis**: Önbellekleme amacıyla kullanılır.
- **Eureka Server**: Servislerin bulunabilirliğini sağlamak için kullanılır.
- **Gateway**: Gelen istekleri yönlendirmek ve güvenlik sağlamak için kullanılır.

---
