package com.benz.subscriber.api.listener;

import com.benz.subscriber.api.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class ProductConsumer{

    private Logger logger= LoggerFactory.getLogger(ProductConsumer.class);

    /*private ObjectMapper objectMapper;*/

   /* @Override
    public void onMessage(Message message, byte[] bytes) {

        try {
            objectMapper = new ObjectMapper();

           Product product= objectMapper.readValue(message.toString(), Product.class);

           logger.info(" productId  : "+product.getProductId());
           logger.info(" productName  : "+product.getName());
           logger.info(" quantity  : "+product.getQty());
           logger.info(" price "+product.getPrice());


        }catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }*/

   public void handleMessage(Product product)
   {

       logger.info(" productId  : "+product.getProductId());
       logger.info(" productName  : "+product.getName());
       logger.info(" quantity  : "+product.getQty());
       logger.info(" price "+product.getPrice());
   }


}
