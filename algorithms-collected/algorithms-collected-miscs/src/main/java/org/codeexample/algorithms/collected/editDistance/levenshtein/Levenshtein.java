package org.codeexample.algorithms.collected.editDistance.levenshtein;

/**
 * The actual algorithm itself is very simple, requiring a matrix to represent
 * the values as the calculation progresses through both strings.
 * <p>
 * Time efficiency: O(n2)
 * <p>
 * Please refer to
 * http://www.codeproject.com/Articles/162790/Fuzzy-String-Matching
 * -with-Edit-Distance
 * 
 * http://professorjava.weebly.com/edit-distance.html
 * 
 */
public class Levenshtein
{
    public Levenshtein()
    {
        super();
    }

    public double compare(
            final String s1, final String s2)
    {
        double retval = 0.0;
        final int n = s1.length();
        final int m = s2.length();
        if (0 == n)
        {
            retval = m;
        }
        else if (0 == m)
        {
            retval = n;
        }
        else
        {
            retval = 1.0 - (compareImpl(s1, s2) / (Math.max(n, m)));
        }
        return retval;
    }

    private double compareImpl(
            final String s1, final String s2)
    {
        int n = s1.length();
        int m = s2.length();
        int matrix[][] = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++)
        {
            matrix[i][0] = i;
        }
        for (int i = 0; i <= m; i++)
        {
            matrix[0][i] = i;
        }

        for (int i = 1; i <= n; i++)
        {
            int s1i = s1.codePointAt(i - 1);
            for (int j = 1; j <= m; j++)
            {
                int s2j = s2.codePointAt(j - 1);
                final int cost = s1i == s2j ? 0 : 1;
                matrix[i][j] = min3(matrix[i - 1][j] + 1, matrix[i][j - 1] + 1,
                        matrix[i - 1][j - 1] + cost);
            }
        }
        return matrix[n][m];
    }

    private int min3(
            final int a, final int b, final int c)
    {
        return Math.min(Math.min(a, b), c);
    }
}