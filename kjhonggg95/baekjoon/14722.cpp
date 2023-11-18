#include <bits/stdc++.h>
#define fastio ios::sync_with_stdio(false), cin.tie(0), cout.tie(0)
#define X first
#define Y second
using namespace std;
using ll = long long;
using pii = pair<int, int>;

int n;
int arr[1001][1001];
pii dp[1001][1001]; // [i][j] : i, j 칸에 도달했을 때 {최근에 마신 우유, 지금까지 마신 우유의 수}

// 0 딸기우유
// 1 초코우유
// 2 바나나우유

// 딸기 -> 초코 -> 바나나 -> 딸기 -> ...

// 이동
// 1) 동쪽
// 2) 남쪽

pii func(int r, int c)
{
    if (r <= 0 || c <= 0)
        return {-1e9, -1e9};

    if (dp[r][c].Y != -1)
        return dp[r][c];

    // 위쪽
    pii ret1 = func(r - 1, c);
    if (arr[r][c] == (dp[r - 1][c].X + 1) % 3)
        ret1 = {arr[r][c], dp[r-1][c].Y + 1};

    // 왼쪽
    pii ret2 = func(r, c - 1);
    if (arr[r][c] == (dp[r][c - 1].X + 1) % 3)
        ret2 = {arr[r][c], dp[r][c - 1].Y + 1};

    return dp[r][c] = (ret1.Y > ret2.Y ? ret1 : ret2);
}

int main()
{
    fastio;
    memset(dp, -1, sizeof(dp));
    cin >> n;
    for (int i = 1; i <= n; i++)
        for (int j = 1; j <= n; j++)
            cin >> arr[i][j];

    dp[1][1] = {2, 0};
    
    if(arr[1][1] == 0)
        dp[1][1] = {0, 1};

    cout << func(n, n).Y << '\n';
}
