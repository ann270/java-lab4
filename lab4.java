// Клас для представлення літери
class Letter {
    private char character;

    public Letter(char character) {
        this.character = character;
    }

    public char getCharacter() {
        return character;
    }

    @Override
    public String toString() {
        return String.valueOf(character);
    }
}

// Клас для представлення слова
class Word {
    private Letter[] letters;

    public Word(String word) {
        letters = new Letter[word.length()];
        for (int i = 0; i < word.length(); i++) {
            letters[i] = new Letter(word.charAt(i));
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Letter letter : letters) {
            sb.append(letter);
        }
        return sb.toString();
    }
}

// Клас для представлення речення
class Sentence {
    private Word[] words;

    public Sentence(String sentence) {
        String[] wordStrings = sentence.trim().split("\\s+");
        words = new Word[wordStrings.length];
        for (int i = 0; i < wordStrings.length; i++) {
            words[i] = new Word(wordStrings[i]);
        }
    }

    public Word[] getWords() {
        return words;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Word word : words) {
            sb.append(word).append(" ");
        }
        return sb.toString().trim();
    }
}

// Клас для представлення тексту
class Text {
    private Sentence[] sentences;

    public Text(String text) {
        String[] sentenceStrings = text.split("\\.");
        sentences = new Sentence[sentenceStrings.length];
        for (int i = 0; i < sentenceStrings.length; i++) {
            sentences[i] = new Sentence(sentenceStrings[i]);
        }
    }

    public Sentence[] getSentences() {
        return sentences;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Sentence sentence : sentences) {
            sb.append(sentence).append(". ");
        }
        return sb.toString().trim();
    }
}

// Основний клас з виконавчим методом
public class lab4 {
    public static void main(String[] args) {
        try {
            // Заданий текст
            String text = "Це перше речення. Це друге речення. А це третє речення.";

            // Створення об'єкту тексту
            Text myText = new Text(text);

            // Перевірка, чи є принаймні одне речення
            if (myText.getSentences().length == 0) {
                throw new IllegalArgumentException("Текст не мiстить жодного речення.");
            }

            // Отримання першого речення та інших речень
            Sentence firstSentence = myText.getSentences()[0];
            StringBuilder subsequentWords = new StringBuilder();

            // Додавання слів з інших речень до StringBuilder
            for (int i = 1; i < myText.getSentences().length; i++) {
                for (Word word : myText.getSentences()[i].getWords()) {
                    subsequentWords.append(word).append(" ");
                }
            }

            // Пошук унікальних слів у першому реченні
            String uniqueWord = null;

            for (Word word : firstSentence.getWords()) {
                String wordString = word.toString().toLowerCase();
                String[] subsequentWordArray = subsequentWords.toString().toLowerCase().split("\\s+");
                boolean isUnique = true;

                // Перевірка наявності слова в масиві subsequentWords
                for (String subsequentWord : subsequentWordArray) {
                    if (subsequentWord.equals(wordString)) {
                        isUnique = false;
                        break;
                    }
                }
                if (isUnique) {
                    uniqueWord = wordString;
                    break; // Зупиняємося після знаходження першого унікального слова
                }
            }

            // Вивід результатів
            if (uniqueWord != null) {
                System.out.println("Перше унiкальне слово в першому реченнi: " + uniqueWord);
            } else {
                System.out.println("В першому реченнi немає унiкальних слiв.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Невiдома помилка: " + e.getMessage());
        }
    }
}
