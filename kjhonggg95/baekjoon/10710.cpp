#include <bits/stdc++.h>
#define fastio ios::sync_with_stdio(false), cin.tie(0), cout.tie(0)
#define X first
#define Y second
using namespace std;
using ll = long long;
using pii = pair<int, int>;

int d[1005];        // 거리
int w[1005];        // 날씨 나쁨정도
int dp[1005][1005]; // [i][j] : i번째 날 j번째 도시를 갈 때 최소 피로도

// 10 25 15
// 50 30 15 40 30

int main()
{
    fastio;
    memset(dp, 0x7f, sizeof(dp));
    int n, m;
    cin >> n >> m;
    for (int i = 1; i <= n; i++)
        cin >> d[i];
    for (int i = 1; i <= m; i++)
        cin >> w[i];

    // 출발지(0번 도시)에서의 피로도는 0
    for (int i = 0; i <= m; i++)
        dp[i][0] = 0;

    for (int i = 1; i <= m; i++)     // 날씨 나쁨정도
        for (int j = 1; j <= n; j++) // 도시
            dp[i][j] = min(dp[i - 1][j], dp[i - 1][j - 1] + d[j] * w[i]);
    
    cout << dp[m][n] << '\n';
}
