package ru.job4j.articles.service.generator;

import ru.job4j.articles.model.Article;
import ru.job4j.articles.model.Word;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RandomArticleGenerator implements ArticleGenerator {
    @Override
    public SoftReference<Article> generate(List<Word> words) {
        var wordsCopy = new ArrayList<>(words);
        Collections.shuffle(wordsCopy);
        var content = wordsCopy.stream()
                .map(Word::getValue)
                .collect(Collectors.joining(" "));
        Article article = new Article(content);
        return new SoftReference<>(article);
    }
}
