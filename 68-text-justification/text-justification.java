import java.util.*;

class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        int n = words.length;
        int i = 0;

        while (i < n) {
            int j = i;          // last word index (exclusive) for this line
            int wordsLen = 0;   // total letters length in this line

            // Greedily pack as many words as possible
            while (j < n) {
                int nextLen = wordsLen + words[j].length();
                int spacesBetween = j - i; // minimum spaces needed between words
                if (nextLen + spacesBetween > maxWidth) break;
                wordsLen += words[j].length();
                j++;
            }

            int numWords = j - i;
            int totalSpaces = maxWidth - wordsLen;

            boolean lastLine = (j == n);
            if (numWords == 1 || lastLine) {
                // Left-justified: single spaces between words, rest at end
                StringBuilder sb = new StringBuilder();
                sb.append(words[i]);
                for (int p = i + 1; p < j; p++) {
                    sb.append(' ').append(words[p]);
                }
                while (sb.length() < maxWidth) sb.append(' ');
                res.add(sb.toString());
            } else {
                // Fully-justified: distribute spaces
                int gaps = numWords - 1;
                int base = totalSpaces / gaps;
                int extra = totalSpaces % gaps; // extra spaces go to left gaps

                StringBuilder sb = new StringBuilder();
                for (int p = i; p < j; p++) {
                    sb.append(words[p]);
                    if (p == j - 1) break;
                    int countSpaces = base + ((p - i) < extra ? 1 : 0);
                    for (int s = 0; s < countSpaces; s++) sb.append(' ');
                }
                res.add(sb.toString());
            }

            i = j;
        }

        return res;
    }
}