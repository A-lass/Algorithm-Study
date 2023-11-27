#include <bits/stdc++.h>
#define fastio ios::sync_with_stdio(false), cin.tie(0), cout.tie(0)
#define X first
#define Y second
using namespace std;
using ll = long long;
using pii = pair<int, int>;

const int MAX_V = 52;
const int INF = 1e9;

int c[MAX_V][MAX_V]; // 용량
int f[MAX_V][MAX_V]; // 유량
vector<int> adj[MAX_V]; // 인접리스트

int prv[MAX_V];

int c2i(char c)
{
    if(c <= 'Z')
        return c - 'A';
    return c - 'a' + 26;
}

int main()
{
    fastio;
    int n;
    cin >> n;
    for(int i = 0;i<n;i++)
    {
        char u, v;
        int w;
        cin >> u >> v >> w;
        u = c2i(u); v = c2i(v);
        c[u][v] = c[v][u] += w;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    int tot = 0; // 총 유량
    int s = c2i('A'); // 소스
    int e = c2i('Z'); // 싱크
    
    while(1)
    {
        memset(prv, -1, sizeof(prv));

        queue<int> q;
        q.push(s);

        while(!q.empty() && prv[e] == -1)
        {
            int cur = q.front(); q.pop();

            for(auto nxt : adj[cur])
            {
                // cur -> nxt 로 흘려보낼 여유가 남아있는지 && nxt를 아직 방문하지 않았는지
                if(c[cur][nxt] - f[cur][nxt] > 0 && prv[nxt] == -1)
                {
                    q.push(nxt);
                    prv[nxt] = cur;
                    if(nxt == e) break; // 싱크 도달시 탈출
                }
            }
        }

        // 싱크로 가는 경로가 더 없으면 루프 탈출
        if(prv[e] == -1) break;

        int flow = INF;

        // 경로상에서 허용치가 가장 낮은 곳 찾기
        for(int i = e;i!=s;i = prv[i])
            flow = min(flow, c[prv[i]][i] - f[prv[i]][i]);

        // 경로에 위에서 찾은 flow만큼 유량 흘려보냄
        for(int i = e;i!=s;i = prv[i])
        {
            f[prv[i]][i] += flow;
            f[i][prv[i]] -= flow;
        }

        tot += flow;
    }

    cout << tot << '\n';
}
