# amcom-order
Projeto para consumir os pedidos que estão no projeto amcom, em uma fila kafka e persistí-los no banco orders, no MySql.

Para executar o projeto, siga os seguintes passos:

1 - Precisa executar o projeto OrderProducer (https://github.com/jcabralbr/amcom/blob/main/README.md)

2 - start da classe OrderConsumerApplication

Endpoint:
========

Existe um endpoint para consultar os pedidos armazenados na base de dados.

A URL é http://localhost:8099/api/orders/{orderId} onde orderId é o número do pedido.

Se o pedido existe, é retornado o status Ok (200), com as informações do pedido.

Se o pedido não existe, é retornado o status Not Found (404) e uma mensagem informando que o pedido não existe.

Exemplos:

1) Quando o pedido não existe (404):
   
curl http://localhost:8099/api/orders/1

  {
      "message":"Pedido não encontrado para o orderId: 1"
  }

2) Quando o pedido for encontrado (200):
   
curl http://localhost:8099/api/orders/2589

    
    {
      "orderId":"2589",
      "items":[
                {
                  "sku":"SKU-816",
                  "quantity":4,
                  "unitPrice":12.66
               }
             ],
      "totalPrice":50.64,
      "orderStatus":"CREATED"
    }
   
  
