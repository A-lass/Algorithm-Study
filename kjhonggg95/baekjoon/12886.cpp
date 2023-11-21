#include <bits/stdc++.h>
#define fastio ios::sync_with_stdio(false), cin.tie(0), cout.tie(0)
#define X first
#define Y second
using namespace std;
using ll = long long;
using pii = pair<int, int>;

bool vis[2005][2005];

struct stone
{
    int a;
    int b;
    int c;
};

int main()
{
    fastio;
    int A, B, C;
    cin >> A >> B >> C;

    queue<stone> q;
    q.push({A, B, C});
    vis[A][B] = true;
    vis[B][C] = true;

    while (!q.empty())
    {
        int a = q.front().a;
        int b = q.front().b;
        int c = q.front().c;
        q.pop();

        if (a == b && b == c)
        {
            cout << 1 << '\n';
            return 0;
        }

        // a b
        if (a > b && !vis[a - b][b + b])
        {
            q.push({a - b, b + b, c});
            vis[a - b][b + b] = true;
        }
        else if (a < b && !vis[a + a][b - a])
        {
            q.push({a + a, b - a, c});
            vis[a + a][b - a] = true;
        }

        // b c
        if (b > c && !vis[b - c][c + c])
        {
            q.push({a, b - c, c + c});
            vis[b - c][c + c] = true;
        }
        else if (b < c && !vis[b + b][c - b])
        {
            q.push({a, b + b, c - b});
            vis[b + b][c - b] = true;
        }

        // c a
        if (c > a && !vis[a + a][c - a])
        {
            q.push({a + a, b, c - a});
            vis[a + a][c - a] = true;
        }
        else if (c < a && !vis[a - c][c + c])
        {
            q.push({a - c, b, c + c});
            vis[a - c][c + c] = true;
        }
    }

    cout << 0 << '\n';
}
