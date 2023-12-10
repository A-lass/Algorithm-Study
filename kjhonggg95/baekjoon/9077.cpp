#include <bits/stdc++.h>
#define fastio ios::sync_with_stdio(false), cin.tie(0), cout.tie(0)
#define X first
#define Y second
using namespace std;
using ll = long long;
using pii = pair<int, int>;

int sum[10011][10011];

int main()
{
    fastio;
    int t;
    cin >> t;
    while (t--)
    {
        memset(sum, 0, sizeof(sum));
        int n, ans = 0;
        cin >> n;
        while (n--)
        {
            int x, y;
            cin >> x >> y;
            for (int i = x; i <= x + 10; i++)
            {
                for (int j = y; j <= y + 10; j++)
                {
                    sum[i][j]++;
                    ans = max(ans, sum[i][j]);
                }
            }
        }

        cout << ans << '\n';
    }
}
