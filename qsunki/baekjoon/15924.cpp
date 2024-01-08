#include <iostream>
#include <algorithm>
#include <queue>
#include <vector>

using namespace std;
using ll = long long;
using pii = pair<int, int>;

char graph[3001][3001];
int num_paths[3001][3001];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    int n, m;
    cin >> n >> m;
    int total_paths = 0;

    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
            cin >> graph[i][j];
        }
    }

    for (int i = n - 1; i >= 0; --i) {
        for (int j = m - 1; j >= 0; --j) {
            if (graph[i][j] == 'X') {
                num_paths[i][j] = 1;
            } else if (graph[i][j] == 'E') {
                num_paths[i][j] = num_paths[i][j + 1];
            } else if (graph[i][j] == 'B') {
                num_paths[i][j] = num_paths[i][j + 1] + num_paths[i + 1][j];
            } else if (graph[i][j] == 'S') {
                num_paths[i][j] = num_paths[i + 1][j];
            }
            num_paths[i][j] %= 1000'000'009;
            total_paths += num_paths[i][j];
            total_paths %= 1000'000'009;
        }
    }

    cout << total_paths;
    return 0;
}
