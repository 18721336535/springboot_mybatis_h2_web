//package com.zbq.config;
//
//import com.zbq.dao.Message;
//import com.zbq.mapper.MessageLineMapper;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.item.database.JpaItemWriter;
//import org.springframework.batch.item.file.FlatFileItemReader;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.json.JsonParseException;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.FileSystemResource;
//
//import javax.persistence.EntityManagerFactory;
//import java.io.File;
//import java.io.Writer;
//@Configuration
//@EnableAutoConfiguration
///**@EnableBatchProcessing是打开Batch。如果要实现多Job的情况，
//需要把EnableBatchProcessing注解的modular设置为true，
//让每个Job使用自己的ApplicationConext */
//@EnableBatchProcessing(modular = true)
//public class MessageMigrationJobConfiguration {
//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job messageMigrationJob(@Qualifier("messageMigrationStep") Step messageMigrationStep) {
//        return jobBuilderFactory.get("messageMigrationJob")
//                .start(messageMigrationStep)
//                .build();
//    }
//
//    @Bean
//    public Step messageMigrationStep(@Qualifier("jsonMessageReader") FlatFileItemReader<Message> jsonMessageReader,
//                                     @Qualifier("messageItemWriter") JpaItemWriter<Message> messageItemWriter,
//                                     @Qualifier("errorWriter") Writer errorWriter) {
//
//
//        return stepBuilderFactory.get("messageMigrationStep")
//                .<Message, Message>chunk(CHUNK_SIZE)
//                .reader(jsonMessageReader).faultTolerant().skip(JsonParseException.class).skipLimit(SKIP_LIMIT)
//                .listener(new MessageItemReadListener(errorWriter))
//                .writer(messageItemWriter).faultTolerant().skip(Exception.class).skipLimit(SKIP_LIMIT)
//                .listener(new MessageWriteListener())
//                .build();
//    }
//
////    @Bean
////    public FlatFileItemReader<Message> jsonMessageReader() {
////        FlatFileItemReader<Message> reader = new FlatFileItemReader<>();
////        reader.setResource(new FileSystemResource(new File(MESSAGE_FILE)));
////        reader.setLineMapper(new MessageLineMapper());
////        return reader;
////    }
////
////    @Autowired
////    private EntityManagerFactory entityManager;
////
////    @Bean
////    public JpaItemWriter<Message> messageItemWriter() {
////        JpaItemWriter<Message> writer = new JpaItemWriter<>();
////        writer.setEntityManagerFactory(entityManager);
////        return writer;
////    }
//}
