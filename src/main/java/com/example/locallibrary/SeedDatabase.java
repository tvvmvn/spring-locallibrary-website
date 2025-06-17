package com.example.locallibrary;

import java.util.HashSet;
import java.time.LocalDate;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.locallibrary.model.Author;
import com.example.locallibrary.model.Book;
import com.example.locallibrary.model.BookInstance;
import com.example.locallibrary.model.Genre;
import com.example.locallibrary.enums.Status;

import com.example.locallibrary.repository.AuthorRepository;
import com.example.locallibrary.repository.BookRepository;
import com.example.locallibrary.repository.GenreRepository;
import com.example.locallibrary.repository.BookInstanceRepository;

@Component
class SeedDatabase implements CommandLineRunner {

  private static final Logger log = LoggerFactory.getLogger(SeedDatabase.class);

  AuthorRepository authorRepository;
  BookRepository bookRepository;
  BookInstanceRepository bookInstanceRepository;
  GenreRepository genreRepository;

  SeedDatabase(AuthorRepository authorRepository, BookRepository bookRepository,
    BookInstanceRepository bookInstanceRepository, GenreRepository genreRepository) {

    this.authorRepository = authorRepository;
    this.bookRepository = bookRepository;
    this.bookInstanceRepository = bookInstanceRepository;
    this.genreRepository = genreRepository;
  }
  
  
  @Override
  public void run(String... args) throws Exception {

    // Genres
    Genre[] genres = {
        new Genre("Fantasy"),
        new Genre("Science Fiction"),
        new Genre("French Poetry")
    };

    for (Genre genre : genres) {
      log.info("Preloaded genre: " + genreRepository.save(genre));
    }

    // Authors
    Author[] authors = {
        new Author("Patrick", "Rothfuss", LocalDate.of(1973, 6, 6), null),
        new Author("Ben", "Bova", LocalDate.of(1932, 11, 8), null),
        new Author("Isaac", "Asimov", LocalDate.of(1920, 1, 2), LocalDate.of(1992, 4, 6)),
        new Author("Bob", "Billings", null, null),
        new Author("Jim", "Jones", LocalDate.of(1971, 12, 16), null),
    };

    for (Author author : authors) {
      log.info("Preloaded author: " + authorRepository.save(author));
    }

    // Books
    Book[] books = {
        new Book("The Name of the Wind (The Kingkiller Chronicle, #1)",
            "I have stolen princesses back from sleeping barrow kings. I burned down the town of Trebon. I have spent the night with Felurian and left with both my sanity and my life. I was expelled from the University at a younger age than most people are allowed in. I tread paths by moonlight that others fear to speak of during day. I have talked to Gods, loved women, and written songs that make the minstrels weep.",
            "9781473211896", authors[0], new HashSet<Genre>(Arrays.asList(genres[0]))),
        new Book("The Wise Man's Fear (The Kingkiller Chronicle, #2)",
            "Picking up the tale of Kvothe Kingkiller once again, we follow him into exile, into political intrigue, courtship, adventure, love and magic... and further along the path that has turned Kvothe, the mightiest magician of his age, a legend in his own time, into Kote, the unassuming pub landlord.",
            "9788401352836", authors[0], new HashSet<Genre>(Arrays.asList(genres[0]))),
        new Book("The Slow Regard of Silent Things (Kingkiller Chronicle)",
            "Deep below the University, there is a dark place. Few people know of it: a broken web of ancient passageways and abandoned rooms. A young woman lives there, tucked among the sprawling tunnels of the Underthing, snug in the heart of this forgotten place.",
            "9780756411336", authors[0], new HashSet<Genre>(Arrays.asList(genres[0]))),
        new Book("Apes and Angels",
            "Humankind headed out to the stars not for conquest, nor exploration, nor even for curiosity. Humans went to the stars in a desperate crusade to save intelligent life wherever they found it. A wave of death is spreading through the Milky Way galaxy, an expanding sphere of lethal gamma ...",
            "9780765379528", authors[1], new HashSet<Genre>(Arrays.asList(genres[1]))),
        new Book("Death Wave",
            "In Ben Bova's previous novel New Earth, Jordan Kell led the first human mission beyond the solar system. They discovered the ruins of an ancient alien civilization. But one alien AI survived, and it revealed to Jordan Kell that an explosion in the black hole at the heart of the Milky Way galaxy has created a wave of deadly radiation, expanding out from the core toward Earth. Unless the human race acts to save itself, all life on Earth will be wiped out...",
            "9780765379504", authors[1], new HashSet<Genre>(Arrays.asList(genres[1]))),
        new Book("Test Book 1", "Summary of test book 1", "ISBN111111", authors[4],
            new HashSet<Genre>(Arrays.asList(genres[0], genres[1]))),
        new Book("Test Book 2", "Summary of test book 2", "ISBN222222", authors[4],
            new HashSet<Genre>(Arrays.asList(genres[0]))),
    };

    for (Book book : books) {
      log.info("Preloaded book: " + bookRepository.save(book));
    }

    // Book instances
    BookInstance[] bookInstances = {
        new BookInstance(books[0], "London Gollancz, 2014.", Status.AVAILABLE, null),
        new BookInstance(books[1], "Gollancz, 2011.", Status.LOANED, LocalDate.of(2024, 12, 25)),
        new BookInstance(books[2], " Gollancz, 2015.", Status.AVAILABLE, null),
        new BookInstance(books[3], "New York Tom Doherty Associates, 2016.", Status.AVAILABLE, null),
        new BookInstance(books[3], "New York Tom Doherty Associates, 2016.", Status.AVAILABLE, null),
        new BookInstance(books[3], "New York Tom Doherty Associates, 2016.", Status.AVAILABLE, null),
        new BookInstance(books[4], "New York, NY Tom Doherty Associates, LLC, 2015.", Status.AVAILABLE, null),
        new BookInstance(books[4], "New York, NY Tom Doherty Associates, LLC, 2015.", Status.MAINTENANCE, null),
        new BookInstance(books[4], "New York, NY Tom Doherty Associates, LLC, 2015.", Status.LOANED,
            LocalDate.of(2024, 12, 25)),
        new BookInstance(books[0], "Imprint XX2", Status.AVAILABLE, null),
        new BookInstance(books[1], "Imprint XXX3", Status.AVAILABLE, null),
    };

    for (BookInstance bookInstance : bookInstances) {
      log.info("Preloaded book instance: " + bookInstanceRepository.save(bookInstance));
    }
  };
}
