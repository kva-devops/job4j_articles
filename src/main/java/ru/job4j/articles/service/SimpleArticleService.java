package ru.job4j.articles.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.articles.model.Article;
import ru.job4j.articles.model.Word;
import ru.job4j.articles.service.generator.ArticleGenerator;
import ru.job4j.articles.store.ArticleStore;
import ru.job4j.articles.store.Store;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.StringWriter;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SimpleArticleService implements ArticleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleArticleService.class.getSimpleName());

    private final ArticleGenerator articleGenerator;

    public SimpleArticleService(ArticleGenerator articleGenerator) {
        this.articleGenerator = articleGenerator;
    }

    @Override
    public void generate(Store<Word> wordStore, int count, Store<Article> articleStore) {
        LOGGER.info("Геренация статей в количестве {}", count);
        var words = wordStore.findAll();
        for (int i = 0; i < count; i++) {
            Article buff = articleGenerator.generate(words);
            LOGGER.info("Сгенерирована статья № {}", i);
            String strBuff = buff.getText();
            System.out.println(strBuff);
            // проверяю, что работоспособность зависит от размера строки передаваемой в статью
            articleStore.save(new Article(Integer.toString(i)));
        }
    }
}
