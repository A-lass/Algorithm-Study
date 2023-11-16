#include <bits/stdc++.h>
#define fastio ios::sync_with_stdio(false), cin.tie(0), cout.tie(0)
#define X first
#define Y second
using namespace std;
using ll = long long;
using pii = pair<int, int>;

int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, 1, 0, -1};

char arr[25][25];
bool vis[25][25][25][25];
int n, m, ans;

struct pos
{
    pii coin1;
    pii coin2;
    int cnt;
};

int main()
{
    fastio;
    pii coin1, coin2;
    int cnt = 0;
    cin >> n >> m;
    for (int i = 1; i <= n; i++)
    {
        string s;
        cin >> s;
        for (int j = 1; j <= m; j++)
        {
            arr[i][j] = s[j - 1];
            if (arr[i][j] == 'o')
            {
                if (cnt == 0)
                {
                    coin1 = {i, j};
                    cnt++;
                }
                else
                    coin2 = {i, j};
            }
        }
    }

    queue<pos> q;
    q.push({coin1, coin2, 0}); // {동전1 위치, 동전2 위치, 이동 횟수}
    vis[coin1.X][coin1.Y][coin2.X][coin2.Y] = true;

    while (!q.empty())
    {
        auto c1 = q.front().coin1; // 동전1 위치
        auto c2 = q.front().coin2; // 동전2 위치
        auto cnt = q.front().cnt;  // 이동 횟수
        q.pop();
        
        // 두 동전의 위치가 겹치는 경우 -> 하나만 떨어뜨리기 불가능
        if(c1 == c2)
            continue;

        // 버튼을 10번보다 많이 누른 경우
        if (cnt >= 11)
            continue;
        
        // 둘 다 떨어지는 경우
        if((c1.X <= 0 || c1.X > n || c1.Y <= 0 || c1.Y > m) && (c2.X <= 0 || c2.X > n || c2.Y <= 0 || c2.Y > m)) 
            continue;

        // 동전1만 떨어지는 경우
        if ((c1.X <= 0 || c1.X > n || c1.Y <= 0 || c1.Y > m) && (c2.X > 0 && c2.X <= n && c2.Y > 0 && c2.Y <= m))
        {
            cout << cnt << '\n';
            return 0;
        }

        // 동전2만 떨어지는 경우
        if ((c2.X <= 0 || c2.X > n || c2.Y <= 0 || c2.Y > m) && (c1.X > 0 && c1.X <= n && c1.Y > 0 && c1.Y <= m))
        {
            cout << cnt << '\n';
            return 0;
        }

        for (int dir = 0; dir < 4; dir++)
        {
            int c1nx = c1.X + dx[dir];
            int c1ny = c1.Y + dy[dir];

            int c2nx = c2.X + dx[dir];
            int c2ny = c2.Y + dy[dir];

            if (arr[c1nx][c1ny] == '#')
            {
                c1nx = c1.X;
                c1ny = c1.Y;
            }

            if (arr[c2nx][c2ny] == '#')
            {
                c2nx = c2.X;
                c2ny = c2.Y;
            }

            if(vis[c1nx][c1ny][c2nx][c2ny] || vis[c2nx][c2ny][c1nx][c1ny]) continue;

            q.push({{c1nx, c1ny}, {c2nx, c2ny}, cnt + 1});
            vis[c1nx][c1ny][c2nx][c2ny] = true;
        }
    }

    cout << -1 << '\n';
    return 0;
}
