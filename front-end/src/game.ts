type Winner = 'X' | 'O' | 'DRAW' | null;

interface GameState {
  cells: Cell[];
  currentPlayer: 'X' | 'O';
  winner: Winner;
  historySize: number;
}

interface Cell {
  text: string;
  playable: boolean;
  x: number;
  y: number;
}

export type { GameState, Cell }