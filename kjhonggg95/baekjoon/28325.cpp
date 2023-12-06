#include <bits/stdc++.h>
#define fastio ios::sync_with_stdio(false), cin.tie(0), cout.tie(0)
#define X first
#define Y second
using namespace std;
using ll = long long;
using pii = pair<int, int>;

ll n;
ll sub[250001];
ll ans;
ll dp[250001][2];

void func(int k)
{
    for(int i = 2;i <= k;i++)
    {
        // i번 개미굴에 개미가 살지 않는 경우
        // 1. i - 1 번에 개미가 살지 않는 경우 + i번 쪽방
        // 2. i - 1 번에 개미가 사는 경우 + i번 쪽방
        dp[i][0] = max(dp[i-1][0], dp[i-1][1]) + sub[i];

        // i번 개미굴에 개미가 사는 경우
        // -> i - 1번에 개미가 살지 않는 경우 + 1
        dp[i][1] = dp[i-1][0] + 1;
    }
}

int main()
{
    fastio;
    cin >> n;
    for(int i = 1;i <= n;i++)
        cin >> sub[i];

    // 1번 개미굴에 개미가 살지 않는 경우
    dp[1][0] = sub[1]; 
    func(n);
    ans = max(dp[n][0], dp[n][1]);

    memset(dp, 0, sizeof(dp));

    // 1번 개미굴에 개미가 사는 경우
    dp[1][1] = 1;
    func(n - 1);
    ans = max({ans, dp[n - 1][0] + sub[n], dp[n - 1][1] + sub[n]});
    
    cout << ans << '\n';
    return 0;
}
