#include <iostream>
#include <algorithm>
#include <cstring>

using namespace std;
using ll = long long;
using pii = pair<int, int>;

int matrix[1001][1001];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    int n, m;
    while (true) {
        cin >> n >> m;
        if (n == 0 && m == 0) break;
        int max_square = 0;
        memset(matrix, 0, sizeof(matrix));
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= m; ++j) {
                cin >> matrix[i][j];
                if (matrix[i][j] == 0) continue;
                int tmp = min({matrix[i - 1][j - 1], matrix[i][j - 1], matrix[i - 1][j]});
                matrix[i][j] = tmp + 1;
                max_square = max(max_square, matrix[i][j]);
            }
        }
        cout << max_square << '\n';
    }

    return 0;
}
