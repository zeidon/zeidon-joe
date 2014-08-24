package com.quinsoft.zeidon.jconsole;

import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * @author dgc
 *
 */
public class OeTableModel extends AbstractTableModel
{
    private static final long serialVersionUID = 1L;
    private List<OeProxy> engines;

    /**
     *
     */
    public OeTableModel( List<OeProxy> engines )
    {
        this.engines = engines;
    }

    /* (non-Javadoc)
     * @see javax.swing.table.TableModel#getColumnCount()
     */
    @Override
    public int getColumnCount()
    {
        return 1;
    }

    /* (non-Javadoc)
     * @see javax.swing.table.TableModel#getRowCount()
     */
    @Override
    public int getRowCount()
    {
        return engines.size();
    }

    /* (non-Javadoc)
     * @see javax.swing.table.TableModel#getValueAt(int, int)
     */
    @Override
    public Object getValueAt( int rowIndex, int colIndex )
    {
        return engines.get( rowIndex ).beanName;
    }

}
